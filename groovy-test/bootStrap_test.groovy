import gorm.recipes.*
import org.hibernate.*
import org.mesis.bila.*
import grails.util.Environment

def bilaService

println ("Nr Rec iniziale di AccountCee: $AccountCee.count")

if (AccountCee.count) {
    AccountCee.executeUpdate("delete Account c")
    AccountCee.executeUpdate("update AccountCee b set b.parent=null where b.parent is not null")
    AccountCee.executeUpdate("delete AccountCee c ")
    println ("dopo delete AccountCee. Nr Rec in AccountCee: $AccountCee.count")
} else
     println ("No delete AccountCee. Nr Rec in AccountCee: $AccountCee.count")
    
//def sessionFactory = ctx.sessionFactory
//def session = sessionFactory.currentSession
//println (session)

new BootStrap().init()

println ("dopo bootstrap. Nr Rec in AccountCee: $AccountCee.count")


"Fine"