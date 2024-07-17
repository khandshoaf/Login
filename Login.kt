import java.io.File
import kotlin.random.Random

data class User(val username:String, val password:String)
val users = ArrayList<User>()

val src = "D:\\BaiTap\\ttUser.txt"

fun saveToFile(){
    val file = File(src)
    file.bufferedWriter().use { out ->
        users.forEach {
            out.write("${it.username},${it.password} \n")
        }
    }
}

fun readFromFile(){
    val file = File(src)
    if(file.exists()){
        file.forEachLine {
            val userInfo = it.split(",")
            if(userInfo.size == 2){
                val(username, password) = userInfo
                users.add(User(username,password))
            }else{
            }
        }
    }
}

fun registerAccount(){
    while(true){
        println("======ĐĂNG KÝ TÀI KHOẢN======")
        print("User: ")
        var enterUsername: String? = readln()
        var usernameExists = false
        for (user in users) {
            if (user.username == enterUsername) {
                usernameExists = true
                break
            }
        }
        if (usernameExists) {
            for(user in users)
            println("Tài khoản đã tồn tại, vui lòng chọn tài khoản khác")
            print("User: ")
            enterUsername = readLine() ?: ""
        }
        print("Pass: ")
        var enterPass:String? = readln()

        if(enterUsername == null) return
        if(enterPass == null) return
        val newUser = User(enterUsername,enterPass)

        if(newUser.username.length <= 6 && newUser.password.length <= 6){
            println("Tài khoản hoặc mật khẩu phải dài hơn 6 ký tự")
            println("---------------------------------------")
        }else if(newUser.username == " " || newUser.password == " " ) {
            println("Tài khoản hoặc mật khẩu không được để trống")
            println("--------------------------------")
        }else{
            users.add(newUser)
            saveToFile()
            println("Đăng ký thành công")
            println("-----------------------------------")
            break
        }
    }
}

fun loginAccount(){
    while(true) {
        println("======ĐĂNG NHẬP VÀO GAME======")
        print("User: ")
        var enterUser:String = readln()
        print("Pass: ")
        var enterPass:String = readln()

        val foundUser = users.find { it.username == enterUser && it.password == enterPass }

        if(foundUser != null){
            println("Đăng nhập thành công")
            break
            println("--------------------------")
        }else{
            println("Tài khoản hoặc mật khẩu không chính xác")
            println("--------------------------")
        }
    }
}

fun forgotAccount(){
    println("======LẤY LẠI MẬT KHẨU======")
    print("User: ")
    var enterUser:String = readln()

    val otp = Random.nextInt(1000,9999)
    println("Mã OTP là: [$otp]")
    val usernameExist = false
    for(user in users){
        if(user.username == enterUser){
            print("Nhập OTP: ")
            var enterOTP:Int = readln().toInt()
            if(enterOTP == otp) {
                println("Mật khẩu của bạn là: [${user.password}]")
                usernameExist == true
                break
            }else{
                println("Mã OTP không đúng")
            }
        }else{
            println("Tài khoản không tồn tại")
        }
    }
}

fun displayInfo(){
    readFromFile()
    println("====DANH SÁCH NGƯỜI DÙNG====")
    for(user in users){
        println("Username: ${user.username}, Passwordd: ${user.password}")
        println("------------------------------------")
    }
}

fun main(args: Array<String>) {
    do{
        println("1.Đăng ký tài khoản")
        println("2.Đăng nhập")
        println("3.Danh sách account đã join.")
        println("4.Lấy lại mật khẩu")
        println("5.Thoát")
        println("===LỰA CHỌN CỦA BẠN===")
        var choose:Int? = readln()?.toInt()
        when(choose){
            1 -> registerAccount()
            2 -> loginAccount()
            3 -> displayInfo()
            4 -> forgotAccount()
            5 -> {println("BẠN ĐÃ THOÁT GAME , see you again")
                break
            }
        }

    }while(choose != 5)
}