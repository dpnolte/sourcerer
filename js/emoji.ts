/* generated @ 2018-11-09T15:28:53.887 */
import { element } from "./element";
import { ElementNode } from "./element.types";
import { LayoutParamAttributes } from "./index.types";
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