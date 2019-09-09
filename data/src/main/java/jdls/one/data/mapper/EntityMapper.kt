package jdls.one.data.mapper

interface EntityMapper<T, V> {

  fun mapFromCached(type: T): V

  fun mapToCached(type: V): T

}