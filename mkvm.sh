#!/bin/zsh

if [[ $# -eq 0 ]]; then
    echo "You forgot the first argument: VM NAME"
    exit 1
fi

VM_NAME=$1

/usr/local/bin/multipass launch focal -n $VM_NAME --cloud-init ./cloud-config.yaml -vvvv
/usr/bin/ssh admin@$VM_NAME.local
