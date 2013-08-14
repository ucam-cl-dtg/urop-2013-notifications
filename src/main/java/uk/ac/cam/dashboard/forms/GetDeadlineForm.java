package uk.ac.cam.dashboard.forms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.QueryParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.ac.cam.dashboard.models.DeadlineUser;
import uk.ac.cam.dashboard.models.User;
import uk.ac.cam.dashboard.queries.DeadlineQuery;
import uk.ac.cam.dashboard.util.Util;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableMap;

public class GetDeadlineForm {
	
	@QueryParam("offset") String offset;
	@QueryParam("limit") String limit;
	
	private Integer intOffset;
	private Integer intLimit;
	
	// Logger
	private static Logger log = LoggerFactory.getLogger(GetNotificationForm.class);
	
	public Map<String, ?> handle(User user, boolean archived) {
		
		Map<String, Object> userDeadlines = new HashMap<String, Object>();

		DeadlineQuery dq = DeadlineQuery.set().byUser(user);
		
		dq.isArchived(archived);
		userDeadlines.put("archived", archived);
		
		// Get number of rows before offset or limit is set
		
		int total = dq.totalRows();
		userDeadlines.put("total", total);
		
		// Impose offset and limit
		
		if (intOffset != null) {
			dq.offset(intOffset);
			userDeadlines.put("offset", intOffset);
		} else {
			userDeadlines.put("offset", 0);
		}
		
		if (intLimit != null) {
			dq.limit(intLimit);
			userDeadlines.put("limit", intLimit);
		} else {
			dq.limit(10);
			userDeadlines.put("limit", 10);
		}
		
		// Process query result set
		
		List<DeadlineUser> results = dq.setList();
		
		List<ImmutableMap<String, ?>> deadlines = new ArrayList<ImmutableMap<String,?>>();
		for (DeadlineUser du : results) {
			deadlines.add(du.toMap());
		}
		
		userDeadlines.put("user", user.toMap());
		userDeadlines.put("deadlines", deadlines);

		log.debug("Returning JSON of user deadlines");
		return userDeadlines;
	}
	
	public ImmutableMap<String, List<String>> validate() {
		ArrayListMultimap<String, String> errors = ArrayListMultimap.create();
		
		// Offset
		if (offset != null && !offset.equals("")) {
			try {
				intOffset = Integer.parseInt(offset);
				if (intOffset < 0) {
					errors.put("limit", "Limit must be greater than or equal to 0");
				}
			} catch(Exception e) {
				errors.put("offset", "Offset must be an integer");
			}
		}
		
		// Limit
		if (limit != null && !limit.equals("")) {
			try {
				intLimit = Integer.parseInt(limit);
				if (intLimit < 0) {
					errors.put("limit", "Limit must be greater than or equal to 0");
				}
			} catch(Exception e) {
				errors.put("limit", "Limit must be an integer");
			}
		}
		
		return Util.multimapToImmutableMap(errors);

	}
	
	public ImmutableMap<String, ?> toMap() {
		ImmutableMap.Builder<String, Object> builder = new ImmutableMap.Builder<String, Object>();
		
		String localOffset = (offset == null ? "" : offset);
		builder.put("offset", localOffset);
		
		String localLimit = (limit == null ? "" : limit);
		builder.put("limit", localLimit);
		
		return builder.build();
	}
	
}