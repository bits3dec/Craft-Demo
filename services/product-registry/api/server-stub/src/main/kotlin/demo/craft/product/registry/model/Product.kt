package demo.craft.product.registry.model

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonValue
import javax.validation.constraints.DecimalMax
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

/**
* 
* Values: QUICKBOOKS_ACCOUNTING,QUICKBOOKS_PAYROLL,QUICKBOOKS_PAYMENTS,TSHEETS
*/
enum class Product(val value: kotlin.String) {

    QUICKBOOKS_ACCOUNTING("QUICKBOOKS_ACCOUNTING"),

    QUICKBOOKS_PAYROLL("QUICKBOOKS_PAYROLL"),

    QUICKBOOKS_PAYMENTS("QUICKBOOKS_PAYMENTS"),

    TSHEETS("TSHEETS");

}

