# -*- mode: ruby -*-
# vi: set ft=ruby :

Vagrant.configure("2") do |config|
  config.env.enable

  # Linux ubuntu 20.04
  config.vm.box = "ubuntu/focal64"

  config.vm.boot_timeout = 600

  config.vm.provider "virtualbox" do |v|
    v.gui = true
  
    v.memory = "4069"
    v.cpus = 2
  end

  # Networking
  config.vm.network :private_network, ip: "10.0.0.10"
  # Web serving from spring
  config.vm.network "forwarded_port", guest: 80, host: 8080

  #
  # View the documentation for the provider you are using for more
  # information on available options.

  # Enable provisioning with a shell script. Additional provisioners such as
  # Ansible, Chef, Docker, Puppet and Salt are also available. Please see the
  # documentation for more information about their specific syntax and use.
  config.vm.provision "shell", inline: <<-SHELL
    # DNS settings
    echo "nameserver 8.8.8.8" | tee /etc/resolv.conf > /dev/null

    # Update
    apt-get update
    
    # Java and Maven
    apt-get install -y openjdk-17-jdk
    echo "export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64/bin/java" >> ~/.bashrc
    source ~/.bashrc

    wget https://dlcdn.apache.org/maven/maven-4/4.0.0-alpha-8/binaries/apache-maven-4.0.0-alpha-8-bin.tar.gz
    tar -xvf apache-maven-4.0.0-alpha-8-bin.tar.gz
    mv apache-maven-4.0.0-alpha-8 /opt/
    ln -s /opt/apache-maven-4.0.0-alpha-8 /opt/maven
    echo 'export M2_HOME=/opt/maven' | tee -a /etc/profile.d/maven.sh
    echo 'export MAVEN_HOME=/opt/maven' | tee -a /etc/profile.d/maven.sh
    echo 'export PATH=${M2_HOME}/bin:${PATH}' | tee -a /etc/profile.d/maven.sh
    source /etc/profile.d/maven.sh

    # Application source code
    cd /srv/
    git clone https://github.com/Vanja-S/user-auth-system
    cd ./user-auth-system/system

    # Setup Database dependencies for the application
    apt-get install -y mysql-server
    systemctl start mysql.service
    sudo mysql -u root -p'root' -e "ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'root'";
    sudo mysql -u root -p'root' -e "FLUSH PRIVILEGES"
    sudo mysql -u root -p'root' < ./sql-scripts/db-script-v2.sql

    # Create service to run application
    mvn clean package
    touch /etc/systemd/system/user-auth-system.service

    echo "[Unit]" | tee -a /etc/systemd/system/user-auth-system.service
    echo "Description=user-auth-system" | tee -a /etc/systemd/system/user-auth-system.service
    echo "After=network.target" | tee -a /etc/systemd/system/user-auth-system.service
    echo "" | tee -a /etc/systemd/system/user-auth-system.service
    echo "[Service]" | tee -a /etc/systemd/system/user-auth-system.service
    echo "ExecStart=java -jar /srv/user-auth-system/system/target/user-auth-system-1.0.0.jar" | tee -a /etc/systemd/system/user-auth-system.service
    echo "" | tee -a /etc/systemd/system/user-auth-system.service
    echo "[Install]" | tee -a /etc/systemd/system/user-auth-system.service
    echo "WantedBy=multi-user.target" | tee -a /etc/systemd/system/user-auth-system.service

    fuser -k 80/tcp

    systemctl daemon-reload
    systemctl enable user-auth-system.service
    systemctl start user-auth-system.service

    # Ingress controller ngrok 
    sudo snap install ngrok
    source /vagrant/.env
    ngrok config add-authtoken $NGROK_AUTHTOKEN

  SHELL
end
