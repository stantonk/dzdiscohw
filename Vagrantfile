# -*- mode: ruby -*-
# vi: set ft=ruby :

Vagrant.configure(2) do |config|
  config.vm.box = "ubuntu/trusty64"


  # forward zookeeper
  config.vm.network "forwarded_port", guest: 2181, host: 2181

  # Create a private network, which allows host-only access to the machine
  # using a specific IP.
  # config.vm.network "private_network", ip: "192.168.33.10"

  # Create a public network, which generally matched to bridged network.
  # Bridged networks make the machine appear as another physical device on
  # your network.
  # config.vm.network "public_network"

   config.vm.provider "virtualbox" do |vb|
     # Customize the amount of memory on the VM:
     vb.memory = "512"
   end

  config.vm.provision "shell", inline: <<-SHELL
    sudo apt-get update --fix-missing
    wget --no-check-certificate https://github.com/aglover/ubuntu-equip/raw/master/equip_java8.sh && bash equip_java8.sh

    wget http://apache.arvixe.com/zookeeper/zookeeper-3.4.7/zookeeper-3.4.7.tar.gz
    mkdir -p ~/var/zookeeper
    tar zxvf zookeeper-3.4.7.tar.gz
    cd zookeeper-3.4.7/
    echo -e "tickTime=2000\ndataDir=/home/vagrant/var/zookeeper\nclientPort=2181" > conf/zoo.cfg
    nohup bin/zkServer.sh start &

  SHELL
end
