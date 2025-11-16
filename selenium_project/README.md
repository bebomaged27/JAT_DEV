# Selenium Test Automation - Checkout Process

This project automates the checkout process on https://practicesoftwaretesting.com/ using a data-driven testing approach with Selenium Java and TestNG.

## Features

- **Data-Driven Testing**: Supports both JSON and CSV test data formats
- **Page Object Model**: Clean and maintainable code structure
- **Explicit Waits**: Professional wait handling without Thread.sleep()
- **Guest Checkout Flow**: Automated guest checkout process
- **Payment Processing**: Cash on Delivery payment method automation
- **Comprehensive Validation**: Order success and cart validation

## Prerequisites

1. Java 21 or higher
2. Maven 3.6 or higher
3. Chrome browser installed

## Setup

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd selenium_project
   ```

2. Install dependencies:
   ```bash
   mvn clean install
   ```

## Test Data

The test uses data from:
- `src/test/resources/test_data/billing_shipping_data.csv` - CSV format test data
- `src/test/resources/test_data/billing_shipping_data.json` - JSON format test data

### Test Data Structure

```json
{
  "login_email": "ledrisayo@necub.com",
  "first_name": "John",
  "last_name": "Doe",
  "street": "123 Main Street",
  "city": "New York",
  "state": "NY",
  "country": "United States",
  "post_code": "12345"
}
```

## Running Tests

Run all tests:
```bash
mvn test
```

Run specific test class:
```bash
mvn test -Dtest=CheckoutTest
```

Run with TestNG suite:
```bash
mvn test -Dsurefire.suiteXmlFiles=testng.xml
```

## Test Reports

### Allure Reports

This project uses Allure for generating beautiful test reports.

**Generate Allure Report:**

1. Run tests and generate Allure results:
   ```bash
   mvn clean test
   ```

2. Generate and open Allure report:
   ```bash
   mvn allure:report
   mvn allure:serve
   ```

   The report will open automatically in your default browser.

3. Generate static HTML report:
   ```bash
   mvn allure:report
   ```
   Report will be available at: `target/site/allure-maven-index.html`

### TestNG HTML Reports

TestNG automatically generates HTML reports in the `test-output` directory after test execution.

View the report:
- Open `test-output/index.html` in your browser after running tests

### Report Features

- **Allure Reports**:
  - Interactive test execution history
  - Test steps with screenshots (if configured)
  - Test categories and severity
  - Timeline and trends
  - Environment information

- **TestNG Reports**:
  - Test execution summary
  - Passed/Failed/Skipped tests
  - Test method details
  - Execution time statistics

## Test Flow

1. Navigate to homepage
2. Select product and add to cart
3. Proceed to checkout
4. Continue as guest (enter email, first name, last name)
5. Fill billing and shipping details
6. Place the order
7. Select payment method (Cash on Delivery) and confirm
8. Validate order success message
9. Validate cart is emptied after purchase

## Project Structure

```
selenium_project/
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── pages/           # Page Object Model classes
│   │       │   ├── BasePage.java
│   │       │   ├── HomePage.java
│   │       │   ├── LoginPage.java
│   │       │   ├── ProductPage.java
│   │       │   ├── CartPage.java
│   │       │   ├── CheckoutPage.java
│   │       │   └── PaymentPage.java
│   │       └── utils/           # Utility classes
│   │           ├── DataLoader.java
│   │           └── LocatorHelper.java
│   └── test/
│       ├── java/
│       │   └── tests/           # Test classes
│       │       └── CheckoutTest.java
│       └── resources/
│           └── test_data/       # Test data files (CSV/JSON)
├── pom.xml                      # Maven configuration
├── testng.xml                   # TestNG configuration
└── README.md                    # Project documentation
```

## Technologies Used

- **Selenium WebDriver 4.38.0**: Browser automation
- **TestNG 7.11.0**: Test framework
- **WebDriverManager 6.3.3**: Automatic driver management
- **Gson 2.10.1**: JSON processing
- **Apache Commons CSV 1.10.0**: CSV processing
- **Maven**: Build and dependency management

## Best Practices

- ✅ Page Object Model pattern for maintainability
- ✅ Explicit waits instead of Thread.sleep()
- ✅ Data-driven testing approach
- ✅ Proper error handling
- ✅ Clean code structure
- ✅ Comprehensive comments

## License

This project is for educational purposes.
