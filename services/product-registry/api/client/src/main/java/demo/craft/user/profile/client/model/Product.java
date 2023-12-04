package demo.craft.user.profile.client.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonValue;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets Product
 */
public enum Product {
  
  QUICKBOOKS_ACCOUNTING("QUICKBOOKS_ACCOUNTING"),
  
  QUICKBOOKS_PAYROLL("QUICKBOOKS_PAYROLL"),
  
  QUICKBOOKS_PAYMENTS("QUICKBOOKS_PAYMENTS"),
  
  TSHEETS("TSHEETS");

  private String value;

  Product(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static Product fromValue(String value) {
    for (Product b : Product.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

