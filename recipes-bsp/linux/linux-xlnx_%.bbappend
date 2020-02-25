FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"

# Add V=1 into this variable to get more verbose output.
# it also appears that linux makefiles save the executed commands as hidden (.) files in the output directory.
EXTRA_OEMAKE_prepend = "DTC_FLAGS_zynq-redpitaya=-@ "

# Currently, this patch only adds the device tree.
SRC_URI_append_redpitaya = " \
		file://0001-Add-devicetree-for-the-redpitaya-OpenMRI-device.patch \
		"
# Modify the kernel config to add Xilinx drivers
SRC_URI += "file://xilinx_drivers.cfg"