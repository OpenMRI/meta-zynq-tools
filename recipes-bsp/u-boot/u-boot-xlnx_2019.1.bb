UBOOT_VERSION = "v2019.01"
XILINX_RELEASE_VERSION = "v2019.1"

UBRANCH ?= "master"

SRCREV ?= "d895ac5e94815d4b45dcf09d4752c5c2334a51db"

SYSROOT_DIRS += "/boot"

DEPENDS += " bison-native"

require recipes-bsp/u-boot/u-boot-xlnx.inc
require recipes-bsp/u-boot/u-boot-spl-zynq-init.inc

SRC_URI_append_redpitaya = " file://0001-Adding-red-pitaya-changes.2019.patch file://0002-Modify-config-for-2017.1.2019.patch file://0003-Updating-config-for-2017.2.2019.patch file://0004-Add-CONFIG_SYS_TEXT_BASE-a-NEW-required-option-in-20.2019.patch file://0005-Add-extra-environment-settings-patch.2019.patch file://0006-Remove-underscores-from-beginning-of-libfdt-header-d.2019.patch file://0007-Changes-for-xilinx-v2018.1-in-yocto.2019.patch file://0001-xilinx-v2019.1-required-changes-to-compile-and-run.patch file://0002-Refactor-zynq_red_pitaya_defconf-to-better-reflect-n.patch"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://README;beginline=1;endline=4;md5=744e7e3bb0c94b4b9f6b3db3bf893897"

# u-boot-xlnx has support for these
HAS_PLATFORM_INIT ?= " \
		zynq_zc702_config \
		zynq_red_pitaya_config \
		"

