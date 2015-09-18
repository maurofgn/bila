import gorm.recipes.*
import org.mesis.bila.*


AccountCee.executeUpdate("update AccountCee b set b.parent=null where b.parent is not null")

AccountCee.executeUpdate("delete AccountCee c ")

println (AccountCee.count)

"Fine"