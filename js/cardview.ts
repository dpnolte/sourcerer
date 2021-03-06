import { ElementNode, element } from './element';
import { LayoutParamAttributes } from './layoutparams';
import { MainTypes } from './main';
// types
/* generated @ 2018-11-13T17:53:10.845 */
export namespace CardviewTypes {
  export interface CardViewAttributes extends MainTypes.FrameLayoutAttributes {
    android_minHeight?: string;
    android_minWidth?: string;
    cardBackgroundColor?: string;
    cardCornerRadius?: string;
    cardElevation?: string;
    cardMaxElevation?: string;
    cardPreventCornerOverlap?: boolean;
    cardUseCompatPadding?: boolean;
    contentPaddingBottom?: string;
    contentPaddingLeft?: string;
    contentPaddingRight?: string;
    contentPaddingTop?: string;
  }
}
// elements
export const CardView = (
  attributes?: CardviewTypes.CardViewAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<CardviewTypes.CardViewAttributes, LayoutParamAttributes> => {
  return element('cardView', attributes, children);
};
