package jdls.one.data.mapper

interface Mapper<in R, out E> {
  fun mapFromApi(raw: R): E
}