/**
 * @author Feng Wang
 * initial version of Item, Account structure, help build annotations for mongo
 */
/**
 * 
 * @author Alex Sobieraj
 * 
 *         Created the original Account Entity object.
 *         Used the Lombok so that we didn't have to create boilerplate code
 *         using Data, AllArgsConstructor, ToString and NoArgsConstructor
 * 
 *         Added validation constraints on our variables
 *         NotNull, NotBlank, Email, Positive, Min, Size, and Enumerated
 * 
 *         When switching over from a sql to a nosql, Feng and I switch the
 *         uid's of the Account
 *         and Item object to a String, and updated the Entity/Id annotations to
 *         Document/Id
 * 
 * 
 * 
 * 
 *
 */
package se452.project.grocery.entities;