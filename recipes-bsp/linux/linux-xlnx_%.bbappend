FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"

# Add V=1 into this variable to get more verbose output.
# it also appears that linux makefiles save the executed commands as hidden (.) files in the output directory.
EXTRA_OEMAKE_prepend = "DTC_FLAGS_zynq-redpitaya=-@ "

# This might be better managed with a patch to linux-xlnx.  However, now I bring in the devicetree source and copy it to the correct place in the code for compilation.
SRC_URI_append_redpitaya = " \
		file://zynq-redpitaya.dts;subdir=redpitaya-devicetree \
		"

do_populate_redpitaya_devicetree() {
	cp ${WORKDIR}/redpitaya-devicetree/* ${S}/arch/arm/boot/dts/
}
addtask populate_redpitaya_devicetree after do_kernel_checkout do_unpack before do_kernel_metadata
