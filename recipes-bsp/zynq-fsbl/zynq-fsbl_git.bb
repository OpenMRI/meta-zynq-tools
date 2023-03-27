
LICENSE = "Unknown"
LIC_FILES_CHKSUM = "file://license.txt;md5=ce611484168a6000bd35df68fc4f4290"

PROVIDES += "virtual/boot-bin"
DEPENDS += "u-boot-xlnx"

inherit deploy

FILESEXTRAPATHS:append := ":${THISDIR}/scripts"
SRC_URI = "git://github.com/Xilinx/embeddedsw.git;branch=xlnx_rel_v2022.2;protocol=https file://app.tcl"

PV = "1.0+git${SRCPV}"
SRCREV = "xilinx_v2022.2"
# SRCREV = "56f3da2afbc817988c9a45b0b26a7fef2ac91706" release-2018.3
# SRCREV = "3c9f0cfde9307c2dc1a298f9f22d492601232821" release-2017.3


XILINX_SDK_VERSION ?= "2022.2"
XILINX_SDK_PATH ?= "/tools/Xilinx/Vitis"
XILINX_SDK_BIN = "${XILINX_SDK_PATH}/${XILINX_SDK_VERSION}/bin"
XILINX_SDK_CC_PATH = "${XILINX_SDK_PATH}/${XILINX_SDK_VERSION}/gnu/aarch32/lin/gcc-arm-none-eabi/bin"

FILES_${PN} += "boot/boot.bin"

S = "${WORKDIR}/git"

do_configure () {
	# Xilinx tool xsct to generate/copy source from appropriate area
	${XILINX_SDK_BIN}/xsct -sdx ${WORKDIR}/app.tcl -ws ${S}/build -pname fsbl -rp ${S}/ -processor ps7_cortexa9_0 -hdf ${LOCAL_HDF_FILE} -arch 32 -app "Zynq FSBL"
}

do_compile () {
	# Run make with path to gnu cross compiler included in Xilinx package
	cd ${S}/build/fsbl
	PATH=${XILINX_SDK_CC_PATH}:$PATH make

	# Package resulting executable.elf with provided .bit file and compiled u-boot.elf
	echo img:{[bootloader] executable.elf ${STAGING_DIR_HOST}/boot/u-boot.elf} > boot_${BP}.bif
	${XILINX_SDK_BIN}/bootgen -image boot_${BP}.bif -o boot.bin -w on
}

do_install () {
	# move the single compiled file of interest so the build system knows it is important
	install -d ${D}/boot
	install ${S}/build/fsbl/boot.bin ${D}/boot/
}

do_deploy () {
	# Make sure the boot.bin shows up in the deploy/image/redpitaya/ directory.
	install -d ${DEPLOYDIR}
	install ${S}/build/fsbl/boot.bin ${DEPLOYDIR}
}

addtask deploy before do_build after do_compile

