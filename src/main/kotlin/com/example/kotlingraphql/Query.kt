package com.example.kotlingraphql

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.coxautodev.graphql.tools.GraphQLResolver
import org.springframework.stereotype.Component

@Component
class Query : GraphQLQueryResolver {
    fun version() = "1"
}

@Component
class PersonQueryResolver(private val personDao : PersonDao) : GraphQLQueryResolver {
    fun getPersonById(id: String) = personDao.getPersonById(id)
}

@Component
class PersonResolver(private val relationshipDao: RelationshipDao) : GraphQLResolver<Person> {
    fun relationships(person: Person, type: String?) =
            when (type) {
                null -> relationshipDao.getRelationshipsFromPerson(person.id)
                else -> relationshipDao.getRelationshipsOfTypeFromPerson(person.id, type)
            }
}

@Component
class RelationshipResolver(private val personDao: PersonDao) : GraphQLResolver<Relationship> {
    fun getFrom(relationship: Relationship) = personDao.getPersonById(relationship.from)
    fun getTo(relationship: Relationship) = personDao.getPersonById(relationship.to)
}

@Component
class PersonMutationResolver(private val personDao: PersonDao) : GraphQLMutationResolver {
    fun createPerson(name: String) = personDao.createPerson(name)
}