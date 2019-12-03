class BankContent : WebContent {

    override fun createAccount(name: String): String {

        return "Account created for " + name;
    }

    override fun getAccountById(id: Int): Account {
        return getAccountFromId(id);
    }

    val Account1 = Account(1,"Tony")
    val Account2 = Account(2,"Mark")
    val Account3 = Account(3,"Hans")


    val myList = listOf<Account>(Account1,Account2,Account3)

    fun getAccountList(list: List<Account>): List<Account>{
        return list;
    }

    fun getAccountFromId(id : Int) : Account {
        for (element in myList) {
            if(element.id == id){
                return element;
            }
        }

        return Account(0, "No owner"); // return an exception instead
    }
}