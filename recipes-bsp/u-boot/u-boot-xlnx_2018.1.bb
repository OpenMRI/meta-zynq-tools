UBOOT_VERSION = "v2018.01"
XILINX_RELEASE_VERSION = "v2018.1"

UBRANCH ?= "master"

SRCREV ?= "1c81b42a326e5b74a5b79e55de9c52b5781b7a8a"

SYSROOT_DIRS += "/boot"

require recipes-bsp/u-boot/u-boot-xlnx.inc
require recipes-bsp/u-boot/u-boot-spl-zynq-init.inc

SRC_URI_append_redpitaya = " file://0001-Adding-red-pitaya-changes.patch file://0002-Modify-config-for-2017.1.patch file://0003-Updating-config-for-2017.2.patch file://0004-Add-CONFIG_SYS_TEXT_BASE-a-NEW-required-option-in-20.patch file://0005-Add-extra-environment-settings-patch.patch file://0006-Remove-underscores-from-beginning-of-libfdt-header-d.patch file://0007-Changes-for-xilinx-v2018.1-in-yocto.patch"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://README;beginline=1;endline=6;md5=157ab8408beab40cd8ce1dc69f702a6c"

# u-boot-xlnx has support for these
HAS_PLATFORM_INIT ?= " \
		zynq_zc702_config \
		zynq_red_pitaya_config \
		"

