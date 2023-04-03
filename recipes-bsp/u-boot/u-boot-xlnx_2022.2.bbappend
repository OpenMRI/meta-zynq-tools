do_notify_configure () {
    bbplain "META-ZYNQ-TOOLS: Applying u-boot-xlnx config overrides"
}

addtask notify_configure before do_configure

FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"
SRC_URI:append = " file://0001-Add-snickerdoodle-config-files.patch \
                   file://0001-Patched-snickerdoodle-CONFIG_SYS_LOAD_ADDR.patch \
                   file://0001-patch-snickerdoodle-device-tree-Makefile.patch \
                   file://0001-Configure-snickerdoodle-to-support-ZYNQ-PL-DUHHHHH.patch \
		   file://0001-Add-redpitaya-device-tree.patch \ 
		   file://0001-Add-redpitaya-defconfig.patch \
		   file://0001-added-UART-debugging.patch "