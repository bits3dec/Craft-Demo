package demo.craft.profile.validator.service.strategy

import demo.craft.profile.validator.entity.enums.ValidationType
import demo.craft.profile.validator.product.integration.QuickBooksAccountingService
import demo.craft.profile.validator.product.integration.QuickBooksPaymentsService
import demo.craft.profile.validator.product.integration.QuickBooksPayrollService
import demo.craft.profile.validator.product.integration.TSheetsService
import demo.craft.profile.validator.service.strategy.impl.*
import org.springframework.stereotype.Component
import javax.transaction.NotSupportedException

/**
 * This class is the central point of validation retriever which stores the map of all the validation types to
 * its specific implementation present in the system.
 * Any validations which is to be done by the system has to go via this layer as a contract.
 *
 * There can be multiple validation rules in the system. The validation rules are designed and maintained in a way such that
 * the common or global level rules are abstracted separately and product specific rules are abstracted to each individual product.
 *
 * As per the above principle we have categorised validation type as -
 * 1. Global level: For e.g, Say there is a rule which says agnostic of product "address cannot be in "FooBar" country.".
 * 2. QuickBooksAccounting level: For e.g, Say this has a product rule and as per its product constraint it "Company Name cannot start with Accounting".
 * 3. QuickBooksPayments level: For e.g, Say this has a product rule and as per its product constraint it "Company Name cannot start with Payments".
 * 4. QuickBooksPayroll level: For e.g, Say this has a product rule and as per its product constraint it "Company Name cannot start with Payroll".
 * 5. TSheets level: For e.g, Say this has a product rule and as per its product constraint it allows all kind of change.
 *
 * The entire profile validation at each level is abstracted and delegated to their specific owner for implementation
 * and shares common strategy contract [ValidationStrategy].
 *
 * Having this class adds an abstract layer between the caller of the validations and the specific validation rule implementation.
 * Any client can simply call this retriever with a [ValidationType] to get the specific strategy on the fly and then call
 * the validate method. This way client can focus only on the part it needs to know and not care of the internals of validation rules.
 */
@Component
class ValidationStrategyRetriever(
    private val quickBooksAccountingService: QuickBooksAccountingService,
    private val quickBooksPaymentsService: QuickBooksPaymentsService,
    private val quickBooksPayrollService: QuickBooksPayrollService,
    private val tSheetsService: TSheetsService
) {
    fun getValidationStrategy(validationType: ValidationType) : ValidationStrategy =
        when (validationType)  {
            ValidationType.GLOBAL -> GlobalLevelProfileValidationStrategy()
            ValidationType.QUICKBOOKS_ACCOUNTING -> QBAccountingProfileValidationStrategy(quickBooksAccountingService)
            ValidationType.QUICKBOOKS_PAYMENTS -> QBPaymentsProfileValidationStrategy(quickBooksPaymentsService)
            ValidationType.QUICKBOOKS_PAYROLL -> QBPayrollValidationStrategy(quickBooksPayrollService)
            ValidationType.TSHEETS -> TSheetsProfileValidationStrategy(tSheetsService)
            else -> {
                throw NotSupportedException("Validation type: $validationType is not supported")
            }
        }
}