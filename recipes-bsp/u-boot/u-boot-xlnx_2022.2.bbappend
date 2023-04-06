do_notify_configure () {
    bbplain "META-ZYNQ-TOOLS: Applying u-boot-xlnx config overrides"
}

addtask notify_configure before do_configure

FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"
SRC_URI:append = " file://0001-Add-snickerdoodle-config-files.patch \
                   file://0002-Patched-snickerdoodle-CONFIG_SYS_LOAD_ADDR.patch \
                   file://0003-patch-snickerdoodle-device-tree-Makefile.patch \
                   file://0004-Configure-snickerdoodle-to-support-ZYNQ-PL-DUHHHHH.patch \
		   file://0005-Add-redpitaya-device-tree.patch \ 
		   file://0006-Add-redpitaya-defconfig.patch \
		   file://0007-added-UART-debugging.patch \
		   file://0008-remove-FIT-support-for-now.patch \
                 "