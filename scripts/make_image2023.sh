DATE=`date +"%H-%M-%S_%d-%b-%Y"`

# default image size if 3GB, which is appropriate for all 4BG SD cards
SIZE=3500

IMAGE=$1_yocto_${DATE}.img

dd if=/dev/zero of=$IMAGE bs=1M count=$SIZE
DEVICE=`losetup -f`

sudo losetup $DEVICE $IMAGE

BOOT_DIR=boot
ROOT_DIR=root

# Create partitions
sudo parted -s $DEVICE mklabel msdos
sudo parted -s $DEVICE mkpart primary fat16   4MB 128MB
sudo parted -s $DEVICE mkpart primary ext4  128MB 100%

BOOT_DEV=/dev/`lsblk -lno NAME -x NAME $DEVICE | sed '2!d'`
ROOT_DEV=/dev/`lsblk -lno NAME -x NAME $DEVICE | sed '3!d'`

# Create file systems
sudo mkfs.msdos -n "BOOT" -v    $BOOT_DEV
sudo mkfs.ext4 -F -j $ROOT_DEV

# Mount file systems
mkdir -p $BOOT_DIR $ROOT_DIR
sudo mount $BOOT_DEV $BOOT_DIR
sudo mount $ROOT_DEV $ROOT_DIR

POKY_BUILD_DIR=~/projects/OpenMRI/poky/build/tmp/deploy/images/$1
sudo tar x -f $POKY_BUILD_DIR/openmri-image-$1.tar.gz  -C $ROOT_DIR
sudo cp $POKY_BUILD_DIR/uImage $BOOT_DIR
sudo cp $POKY_BUILD_DIR/boot.bin $BOOT_DIR
sudo cp $POKY_BUILD_DIR/zynq-$1.dtb $BOOT_DIR
sudo cp $POKY_BUILD_DIR/zynq-$1.dtb $BOOT_DIR/$1.dtb
sudo cp $POKY_BUILD_DIR/boot.scr $BOOT_DIR
# sudo cp $POKY_BUILD_DIR/uEnv.txt $BOOT_DIR

# Unmount file systems
sudo umount $BOOT_DIR $ROOT_DIR
rmdir $BOOT_DIR $ROOT_DIR

sudo losetup -d $DEVICE

# compress the output and delete image
zip -9 $IMAGE.zip $IMAGE
rm $IMAGE

rm current_image.zip
ln -sf $IMAGE.zip current_image.zip
