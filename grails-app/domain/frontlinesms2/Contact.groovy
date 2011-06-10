package frontlinesms2

class Contact {
	String name
	String address

    static constraints = {
		name(blank: true, maxSize: 255, validator: { val, obj ->
				if(val == '') {
					obj.address != ''
					obj.address != null
				}
		})
		address(unique: true, nullable: true, validator: { val, obj ->
				if(val == '') {
					obj.name != ''
					obj.name != null
				}
		})
	}

	def beforeDelete = {
		GroupMembership.deleteFor(this)
	}

	def getGroups() {
		GroupMembership.findAllByContact(this)*.group.sort{it.name}
	}

	def setGroups(groups) {
		this.groups.each() { GroupMembership.remove(this, it) }
		groups.each() { GroupMembership.create(this, it) }
	}

	def addToGroups(Group g, flush=false) {
		GroupMembership.create(this, g, flush)
	}

	def removeFromGroups(Group g, flush=false) {
		GroupMembership.remove(this, g, flush)
	}

	boolean isMemberOf(Group group) {
	   GroupMembership.countByContactAndGroup(this, group) > 0
	}
}
