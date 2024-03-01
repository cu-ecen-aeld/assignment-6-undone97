# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   LICENSE
LICENSE = "Unknown"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f098732a73b5f6f3430472f5b094ffdb"

SRC_URI = "git://github.com/cu-ecen-aeld/assignment-7-undone97;protocol=https;branch=master \
           file://0001-Updated-Makefile-for-yocto-build.patch \
           "

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "d4db527c728e6923406403b1a4212bcd2d397fe5"
inherit module

S = "${WORKDIR}/git"
FILES:${PN} += "../files/S98miscmodules"
inherit update-rc.d
INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME:${PN} = "S98miscmodules"

EXTRA_OEMAKE:append:task-install = " -C ${STAGING_KERNEL_DIR} M=${S}"
EXTRA_OEMAKE:append:task-install = " -C ${STAGING_KERNEL_DIR} M=${S}/scull"
EXTRA_OEMAKE += "KERNELDIR=${STAGING_KERNEL_DIR}"


do_configure () {
	:
}

do_compile () {
	oe_runmake
}

do_install () {
	install -d ${D}${bindir}

	
	install-m 0755 ${S}/misc-modules/module_load ${D}/bin/module_load
	install -m 0755 ${S}/misc-modules/module_unload ${D}/bin/module_unload
	install -m 0755 ${PN} ${D}${sysconfdir}/init.d/

}
