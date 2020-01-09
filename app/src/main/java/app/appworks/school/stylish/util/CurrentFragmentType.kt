package app.appworks.school.stylish.util

import app.appworks.school.stylish.R
import app.appworks.school.stylish.util.Util.getString

enum class CurrentFragmentType(val value: String) {
    HOME(""),
    CATALOG(getString(R.string.catalog)),
    CART(getString(R.string.cart)),
    PROFILE(getString(R.string.profile)),
    PAYMENT(getString(R.string.payment)),
    COLLECT(getString(R.string.collect)),
    HISTORY(getString(R.string.history)),
    COMMENT(getString(R.string.comment)),
    STORE(getString(R.string.store)),
    DETAIL(""),
    CHECKOUT_SUCCESS(getString(R.string.checkout_success_title))
}
