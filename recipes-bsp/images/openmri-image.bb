inherit core-image

SUMMARY = "An image to support the OCRA OpenMRI project."
DISTRO_FEATURES_append += " pam systemd"
TOOLCHAIN_TARGET_TASK_append = " kernel-devsrc"

IMAGE_INSTALL = "${CORE_IMAGE_BASE_INSTALL} flatbuffers zynq-fsbl git curl rsync  cmake openvpn openl2tp zeroconf samba cifs-utils avahi-daemon avahi-utils dtc ntp lighttpd"

IMAGE_FEATURES += "package-management ssh-server-openssh empty-root-password tools-sdk tools-debug dev-pkgs doc-pkgs staticdev-pkgs"
PACKAGE_CLASSES = "package_deb"

REQUIRED_DISTRO_FEATURES= "systemd"

LICENSE = "MIT"

