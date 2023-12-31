rootProject.name = "craft-demo"

include("common:lib")
include("common:lib:cache")
include("common:lib:communication")
include("common:lib:lock")
include("common:domain")
include("services")

include("services:user-profile")
include("services:user-profile:dao")
include("services:user-profile:api:client")
include("services:user-profile:api:server-stub")
include("services:user-profile:common")
include("services:user-profile:domain")

include("services:product-registry")
include("services:product-registry:dao")
include("services:product-registry:api:client")
include("services:product-registry:api:server-stub")
include("services:product-registry:common")
include("services:product-registry:domain")

include("services:user-profile-workflow")
include("services:user-profile-workflow:dao")
include("services:user-profile-workflow:common")
include("services:user-profile-workflow:domain")

include("services:profile-validator")
include("services:profile-validator:dao")
include("services:profile-validator:common")
include("services:profile-validator:domain")
