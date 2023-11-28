package eu.jpereira.trainings.designpatterns.structural.facade.model;

import eu.jpereira.trainings.designpatterns.structural.facade.BookstoreFacade;
import eu.jpereira.trainings.designpatterns.structural.facade.service.*;

public class DefaultBooktoreFacade implements BookstoreFacade {


        private BookDBService bookService;
        private CustomerDBService customerService;
        private OrderingService orderingService;
        private WharehouseService warehouseService;
        private CustomerNotificationService customerNotificationService;


        @Override
        public void placeOrder(String customerId, String isbn) {
            Book dummyBook = bookService.findBookByISBN(isbn);
            Customer dummyCustomer = customerService.findCustomerById(customerId);
            Order dummyOrder = orderingService.createOrder(dummyCustomer, dummyBook);
            DispatchReceipt dummyDispatchReceipt = warehouseService.dispatch(dummyOrder);
            customerNotificationService.notifyClient(dummyDispatchReceipt);
        }

    public void setBookService(BookDBService bookService) {
            this.bookService = bookService;
    }

    public void setCustomerService(CustomerDBService customerService) {
            this.customerService=customerService;
    }

    public void setOrderingService(OrderingService orderingService) {
            this.orderingService=orderingService;
    }

    public void setWharehouseService(WharehouseService warehouseService) {
            this.warehouseService=warehouseService;
    }

    public void setCustomerNotificationService(CustomerNotificationService customerNotificationService) {
            this.customerNotificationService=customerNotificationService;
    }
}


