do_notify_configure () {
    bbplain "META-ZYNQ-TOOLS: Applying device-tree overrides ${THISDIR}/${BPN}/v2022.2"
}

addtask notify_configure before do_compile

FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}/v2022.2:"

# Add V=1 into this variable to get more verbose output.
# it also appears that linux makefiles save the executed commands as hidden (.) files in the output directory.
#EXTRA_OEMAKE:prepend = "DTC_FLAGS_zynq-redpitaya=-@ "

# Currently, this patch only adds the device tree.
SRC_URI:append = " \
		file://0001-Add-devicetree-for-the-redpitaya-OpenMRI-device.patch \
		file://0001-Add-snickerdoodle-device-trees.patch \
		"
# Modify the kernel config to add Xilinx drivers
# SRC_URI:append := "file://xilinx_drivers.cfg"