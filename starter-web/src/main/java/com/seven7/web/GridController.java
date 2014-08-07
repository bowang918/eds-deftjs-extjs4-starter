package com.seven7.web;

import static ch.ralscha.extdirectspring.annotation.ExtDirectMethodType.STORE_MODIFY;
import static ch.ralscha.extdirectspring.annotation.ExtDirectMethodType.STORE_READ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import ch.ralscha.extdirectspring.annotation.ExtDirectMethod;
import ch.ralscha.extdirectspring.bean.ExtDirectStoreReadRequest;
import ch.ralscha.extdirectspring.bean.ExtDirectStoreResult;
import ch.ralscha.extdirectspring.filter.StringFilter;

import com.seven7.domain.entity.User;
import com.seven7.repository.UserRepository;

@Controller
@PreAuthorize("isAuthenticated()")
public class GridController {

	@Autowired
	private UserRepository userRepository;

	@ExtDirectMethod(STORE_READ)
	public ExtDirectStoreResult<User> read(ExtDirectStoreReadRequest storeRequest) {

		String filterValue = null;
		if (!storeRequest.getFilters().isEmpty()) {
			StringFilter filter = (StringFilter) storeRequest.getFilters().iterator().next();
			filterValue = filter.getValue();
		}
		
//
//		List<User> users = db.find(filterValue);
//		int totalSize = users.size();
//
//		Ordering<User> ordering = PropertyOrderingFactory
//				.createOrderingFromSorters(storeRequest.getSorters());
//		if (ordering != null) {
//			users = ordering.sortedCopy(users);
//		}
//
//		if (storeRequest.getStart() != null && storeRequest.getLimit() != null) {
//			users = users.subList(
//					storeRequest.getStart(),
//					Math.min(totalSize,
//							storeRequest.getStart() + storeRequest.getLimit()));
//		}

		return new ExtDirectStoreResult<>(0, null);
	}

	@ExtDirectMethod(STORE_MODIFY)
	public ExtDirectStoreResult<User> create(User newUser) {
//		newUser.setId(UUID.randomUUID().toString());
//		db.update(newUser);
		return new ExtDirectStoreResult<>(newUser);
	}

	@ExtDirectMethod(STORE_MODIFY)
	public ExtDirectStoreResult<User> update(User updatedUser) {
//		db.update(updatedUser);
		return new ExtDirectStoreResult<>(updatedUser);
	}

	@PreAuthorize("hasRole(ADMIN)")
	@ExtDirectMethod(STORE_MODIFY)
	public void destroy(User destroyedUser) {
//		db.delete(destroyedUser);
	}
}
