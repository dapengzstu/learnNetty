package com.learn.netty.demo.protobufexample;

import com.google.protobuf.InvalidProtocolBufferException;

public class Protobuftest {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        AddressBookProtos.Person person1 = AddressBookProtos.Person.newBuilder()
                .setEmail("dapengzstu@163.com")
                .setName("dapeng")
                .setId(20185344)
                .addPhones(
                        AddressBookProtos.Person.PhoneNumber.newBuilder()
                            .setNumber("1111111111")
                            .setType(AddressBookProtos.Person.PhoneType.HOME)
                ).build();


        AddressBookProtos.AddressBook addressBook = AddressBookProtos.AddressBook.newBuilder().addPeople(person1).build();

        //模拟网络传输，打包成一个字节数组
        byte[] addressBookBytes = addressBook.toByteArray();

        //不同的语言可以通过反序列化，把字节数组中的addressBook这个对象弄出来
        AddressBookProtos.AddressBook a = AddressBookProtos.AddressBook.parseFrom(addressBookBytes);

        System.out.println(a.getPeople(0));
    }
}
