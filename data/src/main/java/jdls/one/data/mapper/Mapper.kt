package jdls.one.data.mapper

interface Mapper<R, E> {
  fun map(raw: R): E
  fun reverseMap(entity: E): R
}