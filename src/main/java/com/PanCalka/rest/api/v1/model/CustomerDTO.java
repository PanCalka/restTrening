package com.PanCalka.rest.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    String firstName;
    String name;
    String customerUrl;

    public String getFirstName() {
        return this.firstName;
    }

    public String getName() {
        return this.name;
    }

    public String getCustomerUrl() {
        return this.customerUrl;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCustomerUrl(String customerUrl) {
        this.customerUrl = customerUrl;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof CustomerDTO)) return false;
        final CustomerDTO other = (CustomerDTO) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$firstName = this.getFirstName();
        final Object other$firstName = other.getFirstName();
        if (this$firstName == null ? other$firstName != null : !this$firstName.equals(other$firstName)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$customerUrl = this.getCustomerUrl();
        final Object other$customerUrl = other.getCustomerUrl();
        if (this$customerUrl == null ? other$customerUrl != null : !this$customerUrl.equals(other$customerUrl))
            return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $firstName = this.getFirstName();
        result = result * PRIME + ($firstName == null ? 43 : $firstName.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $customerUrl = this.getCustomerUrl();
        result = result * PRIME + ($customerUrl == null ? 43 : $customerUrl.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof CustomerDTO;
    }

    public String toString() {
        return "CustomerDTO(firstName=" + this.getFirstName() + ", name=" + this.getName() + ", customerUrl=" + this.getCustomerUrl() + ")";
    }
}
