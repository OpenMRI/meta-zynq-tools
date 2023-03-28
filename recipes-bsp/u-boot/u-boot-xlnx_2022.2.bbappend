do_notify_configure () {
    bbplain "TWITZEL: TWITZEL: Applying u-boot-xlnx config overrides"
}

addtask notify_configure before do_configure

FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"
SRC_URI:append = " file://0001-Add-snickerdoodle.patch "