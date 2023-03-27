UBOOT_VERSION = "v2021.01"

UBRANCH ?= "xlnx_rebase_v2021.01"

SRCREV ?= "63b6d260dbe64a005407439e2caeb32da9025954"

include u-boot-xlnx.inc
include u-boot-spl-zynq-init.inc

# Patch required for latest gcc and 2020.2 version of u-boot-xlnx
# This will be removed once the SRCREV above has been updated to include this change
#SRC_URI += " file://0001-Remove-redundant-YYLOC-global-declaration.patch"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://README;beginline=1;endline=4;md5=744e7e3bb0c94b4b9f6b3db3bf893897"

# u-boot-xlnx has support for these
HAS_PLATFORM_INIT ?= " \
		xilinx_zynqmp_virt_config \
		xilinx_zynq_virt_defconfig \
		xilinx_versal_vc_p_a2197_revA_x_prc_01_revA \
		"

