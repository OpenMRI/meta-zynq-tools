inherit core-image

SUMMARY = "An image to support the OCRA OpenMRI project."
DISTRO_FEATURES:append := " pam systemd"
TOOLCHAIN_TARGET_TASK:append = " kernel-devsrc"

IMAGE_INSTALL = "${CORE_IMAGE_BASE_INSTALL} git curl rsync openl2tp zeroconf cifs-utils avahi-daemon avahi-utils dtc ntp lighttpd rt-tests"

IMAGE_FEATURES += "package-management ssh-server-openssh empty-root-password"
PACKAGE_CLASSES = "package_deb"

REQUIRED_DISTRO_FEATURES= "pam systemd"

LICENSE = "MIT"

