# -*- mode: ruby -*-
# vi: set ft=ruby :

require 'vagrant-azure'
require 'dotenv'

Vagrant.configure('2') do |config|
  config.env.enable
  # Use dummy Azure box
  config.vm.box = 'azure-dummy'

  config.ssh.private_key_path = '~/.ssh/user-auto-system-rsa'

  config.vm.provider 'azure' do |az, override|
    az.tenant_id = ENV['AZURE_TENANT_ID']
    az.client_id = ENV['AZURE_CLIENT_ID']
    az.client_secret = ENV['AZURE_CLIENT_SECRET']
    az.subscription_id = ENV['AZURE_SUBSCRIPTION_ID']

    az.vm_name = 'user-auth-system'
    az.vm_size = 'Standard_B1s'
    az.vm_image_urn = 'Canonical:0001-com-ubuntu-server-jammy:22_04-lts:latest'
    az.resource_group_name = 'TechnicalSkils'

    override.vm.synced_folder ".", "/vagrant", disabled: true
  end # config.vm.provider 'azure'
end # Vagrant.configure