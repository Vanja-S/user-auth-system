# -*- mode: ruby -*-
# vi: set ft=ruby :

require 'vagrant-azure'
require 'dotenv'

Vagrant.configure('2') do |config|
  config.env.enable
  # Use vmware fusion provider and ubuntu box
  # https://app.vagrantup.com/bytesguy/boxes/ubuntu-server-20.04-arm64
  config.vm.box = "bytesguy/ubuntu-server-20.04-arm64"
  config.vm.box_version = "1.0.0"

  config.vm.provider "vmware_fusion" do |vmware|
    vmware.vmx["memsize"] = "2048"
    vmware.vmx["numvcpus"] = "1" 
    vmware.gui = true 

    vmware.vmx["keyboardAndMouseProfile"] = "macProfile"
  end

  # Create a user
  config.vm.provision "shell", inline: <<-SHELL
    # Create a new user
    sudo adduser admin --disabled-password --gecos ""

    # Set a password for the new user (replace 'newpassword' with the desired password)
    echo 'admin:admin' | sudo chpasswd
  SHELL

end # Vagrant.configure