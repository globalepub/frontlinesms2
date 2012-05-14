package frontlinesms2.settings

import frontlinesms2.*

class PageSettings extends geb.Page {
	static url = 'settings'
	static at = {
		title.contains('Settings')
	}
	static content = {
		btnApplyLanguage { $('.langsection input.button') }
		languageList { $('select', name:'language') }
		phonesMenuItem(required: false) { $("#settings-menu").children()[0] }
	}
}
