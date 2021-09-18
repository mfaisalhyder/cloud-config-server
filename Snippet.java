// Using a nullable object having a nullable List. Iterate over it and get the Object which matches the condition or else return back-up object.
return Optional
    .ofNullable(new Object())
    .map(Object::getList)
    .map(Collection::stream)
    .map(objList -> objList.filter(objListObj -> objListObj.getId().equals(String.valueOf(objListObj))).findFirst())
    .filter(Optional::isPresent)
    .map(Optional::get)
    .orElseGet(() -> new Object())
;

// Using a non-null object perform actions on it
return Optional.of(new Object())
    .map(Optional::get)
    .map(obj -> (obj.getCreationTime() + Duration.ofMinutes(obj.getTtl()).toMillis()) > currentTimeMillis())
    .filter(aPromise -> aPromise)
    .map(obj -> dataSource.get(key))
    .orElseGet(() -> null)
;

// Iterate over a list of an object then using the same listItem iterate over another list of the parent object and utilize both.
private static List<Object> prepareObjectServicesList(final ObjectDTO objectDTO) {
    return objectDTO
        .getObjectsList()
        .stream()
        .filter(StringUtils::isNotBlank)
        .map(str -> objectDTO
            .getAnotherObjectsList()
            .stream()
            .filter(StringUtils::isNotBlank)
            .map(anotherStr -> prepareObject(str, anotherStr, objectDTO))
            .collect(Collectors.toList())
        )
        .flatMap(List::stream)
        .collect(Collectors.toList())
    ;
}
