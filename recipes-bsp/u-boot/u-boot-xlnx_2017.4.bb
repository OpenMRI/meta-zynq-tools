UBOOT_VERSION = "v2017.01"
XILINX_RELEASE_VERSION = "v2017.4"

UBRANCH ?= "master"

SRCREV ?= "5fa7d2ed066166571e969d036c1871c1759a921d"

SYSROOT_DIRS += "/boot"

require recipes-bsp/u-boot/u-boot-xlnx.inc
require recipes-bsp/u-boot/u-boot-spl-zynq-init.inc

SRC_URI_append_redpitaya = " file://0001-u-boot-xlnx-red-pitaya_u-boot-xlnx-xilinx-v2016.4.patch file://0002-u-boot-xlnx-xilinx-v2017.1.patch file://0003-u-boot-xlnx-xilinx-v2017.2.patch file://0004-u-boot-xlnx-xilinx-v2017.3.patch file://set-extra-env.new.patch file://0001-Remove-underscores-from-beginning-of-libfdt-header-d.patch"

# EXTRA_OEMAKE_append_redpitaya = ' arch=ARM CFLAGS="-O2 -mtune=cortex-a9 -mfpu=neon -mfloat-abi=hard"'

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://README;beginline=1;endline=6;md5=157ab8408beab40cd8ce1dc69f702a6c"

# u-boot-xlnx has support for these
HAS_PLATFORM_INIT ?= " \
		zynq_zc702_config \
		zynq_red_pitaya_config \
		"

