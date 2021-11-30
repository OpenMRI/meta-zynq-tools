# the whole thing needs to be run as root
mkdir /media/rp_boot
mkdir /media/rp_rootfs
kpartx_result=$(kpartx -sav redpitaya_openmri_yocto_xenomai_v2_11302021.img)
read -d \n img_boot_dev img_root_dev <<<$(grep -o 'loop[0-9]*p[0-9]' <<<"$kpartx_result")
mount -o rw -t vfat /dev/mapper/$img_boot_dev /media/rp_boot
mount -o rw -t ext4 /dev/mapper/$img_root_dev /media/rp_rootfs
cp Xenomai/linux-xeno-build/arch/arm/boot/uImage /media/rp_boot/
rsync -arv Xenomai/xeno3_zynq_stage/* /media/rp_rootfs/
rsync -arv Xenomai/zynq_modules/* /media/rp_rootfs/
umount /media/rp_boot
umount /media/rp_rootfs
# remove the mapping again
kpartx -d redpitaya_openmri_yocto_xenomai_v2_11302021.img
dmsetup remove -f /dev/mapper/$img_boot_dev
dmsetup remove -f /dev/mapper/$img_root_dev
