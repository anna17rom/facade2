/**
 * Copyright 2011 Joao Miguel Pereira
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package eu.jpereira.trainings.designpatterns.structural.facade.facade;

import static org.mockito.Mockito.*;

import eu.jpereira.trainings.designpatterns.structural.facade.service.*;
import org.junit.Test;

import eu.jpereira.trainings.designpatterns.structural.facade.BookstoreFacade;
import eu.jpereira.trainings.designpatterns.structural.facade.model.Book;
import eu.jpereira.trainings.designpatterns.structural.facade.model.Customer;
import eu.jpereira.trainings.designpatterns.structural.facade.model.DispatchReceipt;
import eu.jpereira.trainings.designpatterns.structural.facade.model.Order;
import eu.jpereira.trainings.designpatterns.structural.facade.model.DefaultBooktoreFacade;


/**
 * @author windows
 * 
 */
public class BookStoreFacadeTest extends AbstractClientTest {

	@Test
	public void testPlaceOrder() {
		// Dummy literals
		String isbn = "123";
		String customerId = "wall-e";
		Book dummyBook = new Book(isbn);
		Customer dummyCustomer = new Customer(customerId);
		Order dummyOrder = new Order();
		DispatchReceipt dummyDispatchReceipt = new DispatchReceipt();

		// prepare SUT
		BookstoreFacade facade = createFacade();

		// Prepare stubs
		when(bookService.findBookByISBN(isbn)).thenReturn(dummyBook);
		when(customerService.findCustomerById(customerId)).thenReturn(dummyCustomer);
		when(orderingService.createOrder(dummyCustomer, dummyBook)).thenReturn(dummyOrder);
		when(warehouseService.dispatch(dummyOrder)).thenReturn(dummyDispatchReceipt);

		// Exercise SUT
		facade.placeOrder(customerId, isbn);

		// Verify behavior
		verify(warehouseService).dispatch(dummyOrder);
		verify(customerNotificationService).notifyClient(dummyDispatchReceipt);
	}


	protected BookstoreFacade createFacade() {
		DefaultBooktoreFacade impl = new DefaultBooktoreFacade();
		impl.setBookService(bookService);
		impl.setCustomerService(customerService);
		impl.setOrderingService(orderingService);
		impl.setWharehouseService(warehouseService);
		impl.setCustomerNotificationService(customerNotificationService);

		return impl;
	}
}
