import Icon from '@ant-design/icons';
import type { CustomIconComponentProps } from '@ant-design/icons/lib/components/Icon';
import React from 'react';

const FontSvg = () => (
    <svg d="1664876054438" className="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg"
         p-id="14942" width="14" height="14">
        <path
            d="M399.387199 158.662603c17.451464 0 31.600719-14.149255 31.600719-31.600719L430.987918 96.232738c0-17.451464-14.149255-31.600719-31.600719-31.600719-17.451464 0-31.600719 14.149255-31.600719 31.600719l0 30.829146C367.786481 144.513348 381.935736 158.662603 399.387199 158.662603z"
            p-id="14943" fill="#ffffff"></path>
        <path
            d="M624.957655 158.662603c17.451464 0 31.600719-14.149255 31.600719-31.600719L656.558373 96.232738c0-17.451464-14.149255-31.600719-31.600719-31.600719-17.451464 0-31.600719 14.149255-31.600719 31.600719l0 30.829146C593.356936 144.513348 607.506191 158.662603 624.957655 158.662603z"
            p-id="14944" fill="#ffffff"></path>
        <path
            d="M427.2682 229.567489c11.341303 18.258852 42.468231 49.15963 87.164199 49.15963 44.479027 0 75.987648-30.66951 87.596034-48.789193 9.324366-14.56574 5.097088-33.745569-9.350972-43.26027-14.431687-9.499351-33.966603-5.457292-43.666522 8.856715-0.134053 0.200568-13.773701 19.992333-34.57854 19.992333-20.228717 0-32.809243-18.320251-33.662681-19.60143-9.273201-14.59644-28.607548-19.020192-43.332924-9.874905C422.608064 195.262172 418.056398 214.744899 427.2682 229.567489z"
            p-id="14945" fill="#ffffff"></path>
        <path
            d="M498.431983 896.779503 259.370259 896.779503c-40.658002 0-73.734328-33.076326-73.734328-73.734328L185.635931 212.101699c0-40.658002 33.076326-73.734328 73.734328-73.734328 17.451464 0 31.600719-14.149255 31.600719-31.600719s-14.149255-31.600719-31.600719-31.600719c-75.508741 0-136.935766 61.427024-136.935766 136.935766l0 610.943476c0 75.508741 61.427024 136.935766 136.935766 136.935766l239.061724 0c17.451464 0 31.600719-14.149255 31.600719-31.600719C530.032702 910.928758 515.883447 896.779503 498.431983 896.779503z"
            p-id="14946" fill="#ffffff"></path>
        <path
            d="M901.909337 212.101699c0-75.508741-61.432141-136.935766-136.935766-136.935766-17.45658 0-31.600719 14.149255-31.600719 31.600719s14.144138 31.600719 31.600719 31.600719c40.652885 0 73.734328 33.076326 73.734328 73.734328l0 431.874101c0 1.039679 0.207731 2.02103 0.305969 3.035126-3.163039 5.531993-11.175527 14.616906-30.096459 14.616906L658.681734 661.627832c-46.402843 0-93.840248 10.095939-93.840248 84.96716l0 133.947711c0 38.5899 15.08558 64.008826 44.937468 75.596745 6.897084 2.6432 13.475919 3.868097 19.853164 3.868097 20.290115 0 38.543851-12.395308 58.427714-30.783097 0.530073-0.488117 1.043772-0.997724 1.543146-1.527796 212.305337-225.220485 212.305337-261.321704 212.305337-273.186939 0-1.898233-0.178055-3.76372-0.49835-5.587252 0.25685-1.62194 0.49835-3.252067 0.49835-4.945639L901.908314 212.101699zM644.378983 883.534851c-6.907317 6.326079-11.531638 9.772574-14.354939 11.618619-0.781806-1.934049-1.980097-6.259564-1.980097-14.611789L628.043946 746.593969c0-14.385639 2.103918-18.413372 1.939165-18.413372 0 0 0 0-0.005117 0 0.910743-0.606821 6.259564-3.353374 28.704762-3.353374l126.700657 0C747.023758 771.338536 693.851745 831.011614 644.378983 883.534851z"
            p-id="14947" fill="#ffffff"></path>
        <path
            d="M743.911884 523.368932 280.431947 523.368932c-17.451464 0-31.600719 14.149255-31.600719 31.600719 0 17.451464 14.149255 31.600719 31.600719 31.600719l463.479937 0c17.45658 0 31.600719-14.149255 31.600719-31.600719C775.512602 537.518187 761.368464 523.368932 743.911884 523.368932z"
            p-id="14948" fill="#ffffff"></path>
        <path
            d="M743.911884 365.366362 280.431947 365.366362c-17.451464 0-31.600719 14.149255-31.600719 31.600719 0 17.451464 14.149255 31.600719 31.600719 31.600719l463.479937 0c17.45658 0 31.600719-14.149255 31.600719-31.600719C775.512602 379.515616 761.368464 365.366362 743.911884 365.366362z"
            p-id="14949" fill="#ffffff"></path>
    </svg>
);

/**
 * 文字图标
 * @param props
 * @constructor
 */
const FontIcon = (props: Partial<CustomIconComponentProps>) => (
    <Icon component={FontSvg} {...props} />
);

export default FontIcon
