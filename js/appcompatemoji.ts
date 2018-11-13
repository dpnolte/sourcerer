import { ElementNode, element } from './element';
import { LayoutParamAttributes } from './layoutparams';
import { AppcompatTypes } from './appcompat';
import { MainTypes } from './main';
// types
/* generated @ 2018-11-13T17:10:01.252 */
export namespace AppcompatEmojiTypes {
  export interface EmojiAppCompatButtonAttributes extends AppcompatTypes.AppCompatButtonAttributes {
  }
  export interface EmojiAppCompatEditTextAttributes extends AppcompatTypes.AppCompatEditTextAttributes {
    maxEmojiCount?: number;
  }
  export interface EmojiAppCompatTextViewAttributes extends AppcompatTypes.AppCompatTextViewAttributes {
  }
}
// elements
export const EmojiAppCompatTextView = (
  attributes?: AppcompatEmojiTypes.EmojiAppCompatTextViewAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<AppcompatEmojiTypes.EmojiAppCompatTextViewAttributes, LayoutParamAttributes> => {
  return element('emojiAppCompatTextView', attributes, children);
};
export const EmojiAppCompatEditText = (
  attributes?: AppcompatEmojiTypes.EmojiAppCompatEditTextAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<AppcompatEmojiTypes.EmojiAppCompatEditTextAttributes, LayoutParamAttributes> => {
  return element('emojiAppCompatEditText', attributes, children);
};
export const EmojiAppCompatButton = (
  attributes?: AppcompatEmojiTypes.EmojiAppCompatButtonAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<AppcompatEmojiTypes.EmojiAppCompatButtonAttributes, LayoutParamAttributes> => {
  return element('emojiAppCompatButton', attributes, children);
};
