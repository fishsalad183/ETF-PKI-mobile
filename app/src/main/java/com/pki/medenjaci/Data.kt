package com.pki.medenjaci

data class Product(
    val id: Int,
    var name: String,
    var description: String,
    var imgResourceID: Int,
    var price: Int,
    var discountPrice: Int? = null,
)

data class CartItem(
    var product: Product,
    var amount: Int,
    var priceIncludingDiscounts: Int = product.discountPrice ?: product.price,
)

typealias Cart = ArrayList<CartItem>

data class User(
    val id: Int,
    var firstName: String,
    var lastName: String,
    var phone: String,
    var address: String,
    var type: String,
    var username: String,
    var password: String,
    val cart: Cart = Cart(),
    val orders: ArrayList<Cart> = ArrayList<Cart>(),
)

object Data {
    val users = mutableListOf(
        User(1, "Petar", "Petrovic", "0113334444", "Njegoseva 42", "customer", "kupac", "pass"),
        User(2, "Jovan", "Jovanovic", "0612233322", "Zmajeva 123", "salesman", "prodavac", "pass"),
    )

    var currentUser: User? = null

    val products = mutableListOf(
        Product(
            1,
            "Bagremov med 0,5kg",
            "Bagremov med karakterišu prozirna žućkasta boja, veoma nežan ukus i izuzetno blagotvorno dejstvo na organizam. Pogotovo se preporučuje u slučaju respiratornih i gastrointestinalnih problema.",
            R.drawable.proizvod_bagremov_med,
            420,
        ),
        Product(
            2,
            "Bagremov med 1kg",
            "Bagremov med karakterišu prozirna žućkasta boja, veoma nežan ukus i izuzetno blagotvorno dejstvo na organizam. Pogotovo se preporučuje u slučaju respiratornih i gastrointestinalnih problema.",
            R.drawable.proizvod_bagremov_med,
            810,
        ),
        Product(
            3,
            "Lipov med 0,5kg",
            "Med svetle i mutne žućkaste boje, blagog i nešto neobičnijeg ukusa. Pogodan za osobe sa respiratornim i srčanim tegobama.",
            R.drawable.proizvod_lipov_med,
            450,
        ),
        Product(
            4,
            "Lipov med 1kg",
            "Med svetle i mutne žućkaste boje, blagog i nešto neobičnijeg ukusa. Pogodan za osobe sa respiratornim i srčanim tegobama.",
            R.drawable.proizvod_lipov_med,
            880,
        ),
        Product(
            5,
            "Livadski med 0,5kg",
            "Livadski med skupljaju se dobija zahvaljujući trudu vrednih pčelica, koje obilaze mnogobrojne cvetiće po livadama. Reprezentativnog ukusa za medove, i sa obiljem zdravih supstanci, preporučuje se svima.",
            R.drawable.proizvod_livadski_med,
            450,
            390,
        ),
        Product(
            6,
            "Livadski med 1kg",
            "Livadski med skupljaju se dobija zahvaljujući trudu vrednih pčelica, koje obilaze mnogobrojne cvetiće po livadama. Reprezentativnog ukusa za medove, i sa obiljem zdravih supstanci, preporučuje se svima.",
            R.drawable.proizvod_livadski_med,
            870,
        ),
        Product(
            7,
            "Šumski med 0,5kg",
            "Sa najjačim ukusom među vrstama meda, kao i najvećim količinama minerala, ovaj med se najpre preporučuje zdravim osobama kako bi ostale zdrave. Poseduje veoma lepu tamnu ćilibarnu boju.",
            R.drawable.proizvod_sumski_med,
            490,
            430,
        ),
        Product(
            8,
            "Šumski med 1kg",
            "Sa najjačim ukusom među vrstama meda, kao i najvećim količinama minerala, ovaj med se najpre preporučuje zdravim osobama kako bi ostale zdrave. Poseduje veoma lepu tamnu ćilibarnu boju.",
            R.drawable.proizvod_sumski_med,
            950,
            890,
        ),
        Product(
            9,
            "Mleč 0,5kg",
            "Izuzetno lepe beličaste boje i guste, ali i nežne teksture, matični mleč predstavlja kraljevski obrok - i to u bukvalnom smislu - jer ga najpre konzumira kraljica-matica u košnici. Prijatno!",
            R.drawable.proizvod_mlec,
            990,
        ),
        Product(
            10,
            "Mleč 1kg",
            "Izuzetno lepe beličaste boje i guste, ali i nežne teksture, matični mleč predstavlja kraljevski obrok - i to u bukvalnom smislu - jer ga najpre konzumira kraljica-matica u košnici. Prijatno!",
            R.drawable.proizvod_mlec,
            1890,
            1690,
        ),
        Product(
            11,
            "Medovača 0,5L",
            "Ništa lepše no otkinuti se od po-kila kvalitetne rakije. Pogotovo kad tako sladi i klizi.",
            R.drawable.proizvod_medovaca,
            690,
        ),
        Product(
            12,
            "Medovača 1L",
            "Ništa lepše no otkinuti se od po-kila kvalitetne rakije. Pogotovo kad tako sladi i klizi. A ova flajka je taman dovoljno velika da se taj užitak podeli s nekim.",
            R.drawable.proizvod_medovaca,
            1290,
            1190,
        ),
    )

}