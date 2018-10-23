# PUT in rest
Using spring boot, write a simple service that has a single REST endpoint.

PUT /user/{id}
Minimum requirements

The payload for a user should be JSON and have a name and email address.
The PUT endpoint should respond back with an error if any fields are blank or the email is not a valid format.