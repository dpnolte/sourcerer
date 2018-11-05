/* generated @ 2018-11-05T13:38:39.223 */
import "./base.elements";
import "./generated-emoji.attributes";
, import "./generated.attributes";
declare module "LayoutTypes" {
  interface NormalizedEmojiExtractTextLayoutElement<
    LayoutAttributes={}
  > extends NormalizedViewGroupBase<EmojiExtractTextLayoutAttributes, LayoutAttributes> {
    type: "emojiExtractTextLayout";
  }
  interface DenormalizedEmojiExtractTextLayoutElement<
    LayoutAttributes={},
    ChildrenViewAttributes={}
  > extends DenormalizedViewGroupBase<EmojiExtractTextLayoutAttributes, LayoutAttributes, ChildrenViewAttributes, ViewGroupLayoutParamsAttributes> {
    type: "emojiExtractTextLayout";
  }
  interface NormalizedEmojiButtonElement<LayoutAttributes={}> extends ElementBase<EmojiButtonAttributes, LayoutAttributes> {
    type: "emojiButton";
  }
  interface DenormalizedEmojiButtonElement<LayoutAttributes={}> extends ElementBase<EmojiButtonAttributes, LayoutAttributes> {
    type: "emojiButton";
  }
  interface NormalizedEmojiEditTextElement<LayoutAttributes={}> extends ElementBase<EmojiEditTextAttributes, LayoutAttributes> {
    type: "emojiEditText";
  }
  interface DenormalizedEmojiEditTextElement<LayoutAttributes={}> extends ElementBase<EmojiEditTextAttributes, LayoutAttributes> {
    type: "emojiEditText";
  }
  interface NormalizedEmojiTextViewElement<LayoutAttributes={}> extends ElementBase<EmojiTextViewAttributes, LayoutAttributes> {
    type: "emojiTextView";
  }
  interface DenormalizedEmojiTextViewElement<LayoutAttributes={}> extends ElementBase<EmojiTextViewAttributes, LayoutAttributes> {
    type: "emojiTextView";
  }}
