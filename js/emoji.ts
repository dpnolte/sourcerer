import { ElementNode, element } from './element';
import { LayoutParamAttributes } from './layoutparams';
import { MainTypes } from "./main";
// types
/* generated @ 2018-11-12T14:28:05.924 */
export namespace EmojiTypes {
  export interface EmojiButtonAttributes extends MainTypes.ButtonAttributes {
  }
  export interface EmojiEditTextAttributes extends MainTypes.EditTextAttributes {
    maxEmojiCount?: number;
  }
  export interface EmojiExtractTextLayoutAttributes extends MainTypes.LinearLayoutAttributes {
    emojiReplaceStrategy?: number | EmojiReplaceStrategyEnum;
  }
  export enum EmojiReplaceStrategyEnum {
    all = 'all',
    defaultStrategy = 'defaultStrategy',
    nonExistent = 'nonExistent'
  }
  export interface EmojiTextViewAttributes extends MainTypes.TextViewAttributes {
  }
}
// elements
export const EmojiExtractTextLayout = (
  attributes?: EmojiTypes.EmojiExtractTextLayoutAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<EmojiTypes.EmojiExtractTextLayoutAttributes, LayoutParamAttributes> => {
  return element('emojiExtractTextLayout', attributes, children);
};
export const EmojiButton = (
  attributes?: EmojiTypes.EmojiButtonAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<EmojiTypes.EmojiButtonAttributes, LayoutParamAttributes> => {
  return element('emojiButton', attributes, children);
};
export const EmojiEditText = (
  attributes?: EmojiTypes.EmojiEditTextAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<EmojiTypes.EmojiEditTextAttributes, LayoutParamAttributes> => {
  return element('emojiEditText', attributes, children);
};
export const EmojiTextView = (
  attributes?: EmojiTypes.EmojiTextViewAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<EmojiTypes.EmojiTextViewAttributes, LayoutParamAttributes> => {
  return element('emojiTextView', attributes, children);
};