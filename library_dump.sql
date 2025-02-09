PGDMP                     
    w         	   librarydb    12.0    12.0 #    +           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            ,           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            -           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            .           1262    16394 	   librarydb    DATABASE     �   CREATE DATABASE librarydb WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'English_India.1252' LC_CTYPE = 'English_India.1252';
    DROP DATABASE librarydb;
                postgres    false            /           0    0    DATABASE librarydb    ACL     )   GRANT ALL ON DATABASE librarydb TO arun;
                   postgres    false    2862            �            1259    16403    hibernate_sequence    SEQUENCE     {   CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.hibernate_sequence;
       public          arun    false            �            1259    16436    lib_borrower    TABLE       CREATE TABLE public.lib_borrower (
    bor_id bigint NOT NULL,
    user_id bigint NOT NULL,
    book_id bigint NOT NULL,
    issued_by bigint NOT NULL,
    date_of_issue timestamp without time zone NOT NULL,
    date_of_return timestamp without time zone NOT NULL
);
     DROP TABLE public.lib_borrower;
       public         heap    postgres    false            0           0    0    TABLE lib_borrower    ACL     H   GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.lib_borrower TO arun;
          public          postgres    false    207            �            1259    16395    lib_inventory    TABLE     f  CREATE TABLE public.lib_inventory (
    book_id bigint NOT NULL,
    book_name character varying(128) NOT NULL,
    book_desc character varying(256) NOT NULL,
    book_isbn character varying(256),
    book_author character varying(128) NOT NULL,
    book_publication character varying(128) NOT NULL,
    book_category character varying(32) NOT NULL,
    book_media_type character varying(16) NOT NULL,
    book_price character varying(8) NOT NULL,
    book_edition character varying(16) NOT NULL,
    book_induction_date timestamp without time zone NOT NULL,
    book_availability character varying(1) NOT NULL
);
 !   DROP TABLE public.lib_inventory;
       public         heap    postgres    false            1           0    0    TABLE lib_inventory    ACL     1   GRANT ALL ON TABLE public.lib_inventory TO arun;
          public          postgres    false    202            �            1259    16412    lib_role    TABLE     l   CREATE TABLE public.lib_role (
    role_id bigint NOT NULL,
    role_name character varying(30) NOT NULL
);
    DROP TABLE public.lib_role;
       public         heap    postgres    false            2           0    0    TABLE lib_role    ACL     ,   GRANT ALL ON TABLE public.lib_role TO arun;
          public          postgres    false    205            �            1259    16405    lib_user    TABLE     �   CREATE TABLE public.lib_user (
    user_id bigint NOT NULL,
    user_name character varying(36) NOT NULL,
    user_encryted_password character varying(128) NOT NULL,
    enabled integer NOT NULL
);
    DROP TABLE public.lib_user;
       public         heap    postgres    false            3           0    0    TABLE lib_user    ACL     ,   GRANT ALL ON TABLE public.lib_user TO arun;
          public          postgres    false    204            �            1259    16419 	   user_role    TABLE     t   CREATE TABLE public.user_role (
    id bigint NOT NULL,
    user_id bigint NOT NULL,
    role_id bigint NOT NULL
);
    DROP TABLE public.user_role;
       public         heap    postgres    false            4           0    0    TABLE user_role    ACL     -   GRANT ALL ON TABLE public.user_role TO arun;
          public          postgres    false    206            (          0    16436    lib_borrower 
   TABLE DATA                 public          postgres    false    207   �%       #          0    16395    lib_inventory 
   TABLE DATA                 public          postgres    false    202   �&       &          0    16412    lib_role 
   TABLE DATA                 public          postgres    false    205   �+       %          0    16405    lib_user 
   TABLE DATA                 public          postgres    false    204   x,       '          0    16419 	   user_role 
   TABLE DATA                 public          postgres    false    206   �-       5           0    0    hibernate_sequence    SEQUENCE SET     A   SELECT pg_catalog.setval('public.hibernate_sequence', 42, true);
          public          arun    false    203            �
           2606    16402    lib_inventory lib_books_pk 
   CONSTRAINT     ]   ALTER TABLE ONLY public.lib_inventory
    ADD CONSTRAINT lib_books_pk PRIMARY KEY (book_id);
 D   ALTER TABLE ONLY public.lib_inventory DROP CONSTRAINT lib_books_pk;
       public            postgres    false    202            �
           2606    16440    lib_borrower lib_borrower_pk 
   CONSTRAINT     ^   ALTER TABLE ONLY public.lib_borrower
    ADD CONSTRAINT lib_borrower_pk PRIMARY KEY (bor_id);
 F   ALTER TABLE ONLY public.lib_borrower DROP CONSTRAINT lib_borrower_pk;
       public            postgres    false    207            �
           2606    16416    lib_role lib_role_pk 
   CONSTRAINT     W   ALTER TABLE ONLY public.lib_role
    ADD CONSTRAINT lib_role_pk PRIMARY KEY (role_id);
 >   ALTER TABLE ONLY public.lib_role DROP CONSTRAINT lib_role_pk;
       public            postgres    false    205            �
           2606    16418    lib_role lib_role_uk 
   CONSTRAINT     T   ALTER TABLE ONLY public.lib_role
    ADD CONSTRAINT lib_role_uk UNIQUE (role_name);
 >   ALTER TABLE ONLY public.lib_role DROP CONSTRAINT lib_role_uk;
       public            postgres    false    205            �
           2606    16409    lib_user lib_user_pk 
   CONSTRAINT     W   ALTER TABLE ONLY public.lib_user
    ADD CONSTRAINT lib_user_pk PRIMARY KEY (user_id);
 >   ALTER TABLE ONLY public.lib_user DROP CONSTRAINT lib_user_pk;
       public            postgres    false    204            �
           2606    16411    lib_user lib_user_uk 
   CONSTRAINT     T   ALTER TABLE ONLY public.lib_user
    ADD CONSTRAINT lib_user_uk UNIQUE (user_name);
 >   ALTER TABLE ONLY public.lib_user DROP CONSTRAINT lib_user_uk;
       public            postgres    false    204            �
           2606    16423    user_role user_role_pk 
   CONSTRAINT     T   ALTER TABLE ONLY public.user_role
    ADD CONSTRAINT user_role_pk PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.user_role DROP CONSTRAINT user_role_pk;
       public            postgres    false    206            �
           2606    16425    user_role user_role_uk 
   CONSTRAINT     ]   ALTER TABLE ONLY public.user_role
    ADD CONSTRAINT user_role_uk UNIQUE (user_id, role_id);
 @   ALTER TABLE ONLY public.user_role DROP CONSTRAINT user_role_uk;
       public            postgres    false    206    206            �
           2606    16446 "   lib_borrower lib_borrower_book_fk2    FK CONSTRAINT     �   ALTER TABLE ONLY public.lib_borrower
    ADD CONSTRAINT lib_borrower_book_fk2 FOREIGN KEY (book_id) REFERENCES public.lib_inventory(book_id);
 L   ALTER TABLE ONLY public.lib_borrower DROP CONSTRAINT lib_borrower_book_fk2;
       public          postgres    false    202    2706    207            �
           2606    16441 "   lib_borrower lib_borrower_user_fk1    FK CONSTRAINT     �   ALTER TABLE ONLY public.lib_borrower
    ADD CONSTRAINT lib_borrower_user_fk1 FOREIGN KEY (user_id) REFERENCES public.lib_user(user_id);
 L   ALTER TABLE ONLY public.lib_borrower DROP CONSTRAINT lib_borrower_user_fk1;
       public          postgres    false    207    2708    204            �           826    16451    DEFAULT PRIVILEGES FOR TABLES    DEFAULT ACL     �   ALTER DEFAULT PRIVILEGES FOR ROLE arun IN SCHEMA public REVOKE ALL ON TABLES  FROM arun;
ALTER DEFAULT PRIVILEGES FOR ROLE arun IN SCHEMA public GRANT SELECT,INSERT,DELETE,UPDATE ON TABLES  TO arun;
          public          arun    false            �
           2606    16426    user_role user_role_fk1    FK CONSTRAINT     ~   ALTER TABLE ONLY public.user_role
    ADD CONSTRAINT user_role_fk1 FOREIGN KEY (user_id) REFERENCES public.lib_user(user_id);
 A   ALTER TABLE ONLY public.user_role DROP CONSTRAINT user_role_fk1;
       public          postgres    false    204    206    2708            �
           2606    16431    user_role user_role_fk2    FK CONSTRAINT     ~   ALTER TABLE ONLY public.user_role
    ADD CONSTRAINT user_role_fk2 FOREIGN KEY (role_id) REFERENCES public.lib_role(role_id);
 A   ALTER TABLE ONLY public.user_role DROP CONSTRAINT user_role_fk2;
       public          postgres    false    2712    205    206            (   �   x��ӽ
�0�Oq�V��M�ur�P�����b$m�^I�tr�2�����M�.�X�+�XQ�èV�m��J�#qU�`����j++.n1T�Vr}�z^�l�9`7_l�DHH��!��C�!2@�!f��/5}�����sґ�~)����t�i�4��t��l:��`T��?����S�;� �� Z1      #   1  x��]w�8���)rי3u�ڽB�V�b=B�g�zR���Ў��7	��immgv�x#	�H���y	�}����� ������;>�0��|����;�Q�E'^Q$�}XQ�N"ZT�QJ��eV�c�oQ�b���t�;�)��ev�t'	����;��1��'DtO�ξ�[���r��(�8t{v��сs��8d��c�<a@��9���$M@��IH��p���N���}5 �vi�a`y�8����껶i��qy����'����k�c�Vߴ�}���uE�GU�(��P ���	TNTq~����Q&��:=�L׾��DB�G#̺aHP�y��d��i�r6eH#v��8LQP�Ԁ�����(�dt���0�I��P�
��P��TO <Q���r}�i��!-U�7o��
Ń�h� %	ND�)f�+��I)���(�+g�#A�	K+���\�U���a��a��>��D�-��H	H�>@4�Z&8أ8�cu#@!�3
f?1�EMp0*��4���w�3eq�>X�E0r�M۸,k�,V�R��������:���%���#8���7LiTtÝƢ�R{��ELU�p���.Jњ��ߎm:� ��j5�U�>��Lua��I:#�e���-���
A�K��5���%$:5!ѩ����2<�	�0�l�L���֡�[�;Lf	���9��ʊH6q�˿"?����i�8��)o�Ծ@!N�Ȭ����N�7K���{�o�y��~ǣ$N_���˫2%�#Z-9�N-��?֌O-����a}�c�cV�h"<��X"�_��i������X_����枲�t���qZ���EV�s��";X(_��^ȉ_��X��������N�mWf������8��!ZޗȄ��D�v5'�׶)��I�w��E0�ј��pk\V.��l#�?�<����IZMe�;�,;+�m�B�q���S�,?2 �DCvS��4�Ͽ0��a2�Y�8I9���ZF�	E�E3��hUkhM�WA�F�b�1Όa��&�l���N�����}v��b���.b�.��JQ+K��3��0�6o�TM����ܪ����Z�ݜ��U�W�-�$�q��c��\~U^]2������b^����^7s�q�����ө�ʚ~T�p[q�o�{����&q�*������<|9,*��$�'����EmEXj]>��Zea���{i���G�LQ��t�{3k��m��p��-�+l�7ړ�U{�P��8F`@�,��(�I8bL|��_������m^�s�;sa�|%�s�`m����6      &   m   x���v
Q���W((M��L���L�/��IU� ��):
`F^bn��B��O�k������z���k��������5�'�F��
p
����<c�y!�.�~! ø� �G�      %   N  x����n�@��O��DMȥ�MWx��U��1�3� �uЧ/mLjҵ��qN�-��;K{�h��`r
���&8�
���g#8f~#0�%JO� x̃�j��[Ai G�'oܥ��13 �R~�U_����Iy��^�U��s>�C�*�V>o�-$���aL[Y4��> ዸ��Go=�aT�V�B��{l�Ӱ���� S�"K�[�2OpV�:I8������[��V��p������O˶����������Q��Ns	�V�F�CxB5�n�Z�'M\G�J����{xm���m_1����D�Y�-��d%F��͆u�'�b����������/      '   i   x���v
Q���W((M��L�+-N-�/��IU��L�Q sA���������a�� B��\��b6ňBS��F�(Sh�	��M1�Q0�<\����� O(�     