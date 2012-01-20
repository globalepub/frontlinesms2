package frontlinesms2

import java.util.Date

class Dispatch {
	static belongsTo = [message: Fmessage]
	String dst
	DispatchStatus status
	Date dateSent
	
	boolean isDeleted
	
	static constraints = {
		dst(nullable:false)
		status(nullable: false, validator: { val, obj ->
				if(val == DispatchStatus.SENT)
					obj.dateSent != null
				else
					obj.dateSent == null
		})
		dateSent(nullable: true, validator: { val, obj ->
				if(val)
					obj.status == DispatchStatus.SENT
				else
					obj.status != DispatchStatus.SENT
		})
		isDeleted(nullable: true, validator: { val, obj ->
				if(val)
					obj.message.isDeleted
		})
	}
}