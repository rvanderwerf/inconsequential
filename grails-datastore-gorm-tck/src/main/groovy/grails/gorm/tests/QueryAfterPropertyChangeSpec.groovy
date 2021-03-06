package grails.gorm.tests

/**
 * Created by IntelliJ IDEA.
 * User: graemerocher
 * Date: Sep 6, 2010
 * Time: 4:02:40 PM
 * To change this template use File | Settings | File Templates.
 */
class QueryAfterPropertyChangeSpec extends GormDatastoreSpec {

  void "Test that an entity is de-indexed after a change to an indexed property"() {
    given:
      def person = new Person(firstName:"Homer", lastName:"Simpson").save(flush:true)

    when:
      session.clear()
      person = Person.findByFirstName("Homer")
    then:
      person != null

    when:
      person.firstName = "Marge"
      person.save(flush:true)
      session.clear()
      person = Person.findByFirstName("Homer")

    then:
      Person.findByFirstName("Marge") != null
      person == null



  }
}
