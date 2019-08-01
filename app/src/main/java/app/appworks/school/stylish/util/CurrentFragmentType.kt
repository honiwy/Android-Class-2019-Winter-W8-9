package app.appworks.school.stylish.util

import app.appworks.school.stylish.R
import app.appworks.school.stylish.StylishApplication

enum class CurrentFragmentType(val value: String) {
    HOME(""),
    CATALOG(StylishApplication.instance.getString(R.string.catalog)),
    CART(StylishApplication.instance.getString(R.string.cart)),
    PROFILE(StylishApplication.instance.getString(R.string.profile)),
    PAYMENT(StylishApplication.instance.getString(R.string.payment)),
    DETAIL(""),
    CHECKOUT_SUCCESS(StylishApplication.instance.getString(R.string.checkout_success_title))
}
